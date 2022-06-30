package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.dto.BaseResponse;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.service.RecipeService;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author Ali Tofigh 6/28/2022 5:09 PM
 */

@Api(value = "User management", tags = "These services are to add user, grant user, activate services, and ...")
@RestController
@RequestMapping("/api/v1")
public class RecipeController {
    private static Log logger = LogFactory.getLog(RecipeController.class);

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ApiOperation(value = "List all of recipes.", response = String.class)
    @GetMapping("/list-all-recipe")
    public String listAllRecipe() {
        return new Gson().toJson(recipeService.listAllRecipe());
    }

    @ApiOperation(value = "Get a recipe by recipeId", response = String.class)
    @GetMapping("find-recipe-by-vegetarian")
    public String findRecipeById(@RequestParam("vegetarian") boolean vegetarian) {
        List<RecipeDTO> recipeDTOList = recipeService.findRecipeByVegetarian(vegetarian);
        return new Gson().toJson(recipeDTOList);
    }

    @GetMapping("find-recipe-by-instruction")
    public String findRecipeByInstruction(@RequestParam("search-text") String text) {
        List<RecipeDTO> recipeDTOList = recipeService.findRecipeByInstruction(text);
        return new Gson().toJson(recipeDTOList);
    }

    @ApiOperation(value = "Add a new recipe", response = String.class)
    @PostMapping(value = "/add-recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addRecipe(@RequestBody RecipeDTO recipeDTO) {

        recipeService.saveRecipe(recipeDTO);
        return "recipe saved successfully.";
    }

    @ApiOperation(value = "Update a recipe", response = String.class)
    @PostMapping(value = "/update-recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateRecipe(@RequestBody RecipeDTO recipeDTO) throws NotFoundException {
        RecipeDTO recipeD = recipeService.findRecipeById(recipeDTO.getRecipeId());
        //recipeService.updateRecipe(recipeDTO);
        if (recipeD != null) {
            recipeService.saveRecipe(recipeDTO);
            return "recipe updated successfully.";
        } else {
            return "There is not any recipe with id: " + recipeDTO.getRecipeId();
        }
    }

    @ApiOperation(value = "Remove a recipe", response = String.class)
    @GetMapping("/remove-recipe")
    public String deleteRecipe(@RequestParam("recipe-id") long recipeId) {
        int result = recipeService.removeRecipeById(recipeId);
        if (result > 0)
            return "recipe with id: " + recipeId + " removed.";
        else
            return "there is no recipe with id: " + recipeId;

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleError (HttpServletRequest req, Exception exception) {
        if (exception instanceof ClassCastException) {
            return new ResponseEntity<>(new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    exception.getMessage(), req.getMethod() + " - " + req.getRequestURI(), req.getRemoteUser()),
                    HttpStatus.NOT_ACCEPTABLE);
        } else if (exception instanceof ArrayIndexOutOfBoundsException) {
            return new ResponseEntity<>(new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    exception.getMessage(), req.getMethod() + " - " + req.getRequestURI(), req.getRemoteUser()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
        logger.error("Exception Request[" + req.getRequestURI() + "] raised " + exception, exception);
        return new ResponseEntity<>(new BaseResponse("0",exception.getMessage(),
                req.getMethod() + " - " + req.getRequestURI(), ""), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<BaseResponse> handleMediaTypeError(HttpMediaTypeNotSupportedException exception) {
        logger.debug("Request processed: Response=media.error");
        return new ResponseEntity<>(new BaseResponse("media.error", exception.getMessage(),
                exception.getLocalizedMessage(), Objects.requireNonNull(exception.getContentType()).getType()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> handleNotFoundException(HttpMediaTypeNotSupportedException exception) {
        logger.debug("Request processed: Response=media.error");
        return new ResponseEntity<>(new BaseResponse("media.error", exception.getMessage(),
                exception.getLocalizedMessage(), Objects.requireNonNull(exception.getContentType()).getType()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleBindingError(MethodArgumentNotValidException exception) {
        logger.info("Request processed: Response=validation.error");
        logger.info(exception.getBindingResult().getFieldError().getField() + " "
                + exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(new BaseResponse("validation.error", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> handleJsonError(HttpMessageNotReadableException exception) {
        String cause = "incorrect.post.values";
        if (exception.getCause() instanceof UnrecognizedPropertyException)
            cause = "unknown.property." + ((UnrecognizedPropertyException) exception.getCause()).getPropertyName();
        logger.debug("Request processed: Response=" + cause);
        return new ResponseEntity<>(new BaseResponse("httpBadRequest", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
