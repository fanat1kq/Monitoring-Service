package org.example.in.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exception.AuthorizeException;
import org.example.exception.ValidationParametersException;
import org.example.in.mappers.IndicationsMapper;
import org.example.in.security.Authentication;
import org.example.model.Indications;
import org.example.service.ReadingService;
import org.example.service.UserService;
import org.example.exception.*;
import org.example.in.dto.ExceptionResponse;
import org.example.in.dto.SuccessResponse;
import org.example.in.dto.TransactionHistoryResponse;
import org.example.in.dto.TransactionRequest;
import org.example.model.User;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/reading/*")
public class ReadingServlet extends HttpServlet {

    private UserService userService;
    private ReadingService readingService;
    private ObjectMapper jacksonMapper;
    private IndicationsMapper indicationsMapper;
    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
        readingService = (ReadingService) getServletContext().getAttribute("readingService");
        indicationsMapper = (IndicationsMapper) getServletContext().getAttribute("indicationsMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try {
                if (req.getRequestURI().endsWith("/history")) {
                    indicationsHistoryProcess(req, resp, authentication);
                }
            } catch (PlayerNotFoundException | ValidationParametersException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (AuthorizeException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (RuntimeException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(authentication.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try(ServletInputStream inputStream = req.getInputStream()) {
                TransactionRequest request = jacksonMapper.readValue(inputStream, TransactionRequest.class);

                requestValidation(request);

                User user = userService.getByLogin(request.getPlayerLogin());
                Indications indications = new Indications();
                if (!authentication.getLogin().equals(user.getName())) throw new AuthorizeException("Incorrect credentials.");


                String requestURI = req.getRequestURI();//что в юрл запостили
                if (requestURI.endsWith("/put")) {
                    readingService.putReading(indications.getName(),indications.getIndicationsId(),  indications.getValue(), indications.getDate());//вызов метода
                }

                resp.setStatus(HttpServletResponse.SC_OK);
                jacksonMapper.writeValue(resp.getWriter(), new SuccessResponse("Transaction completed successfully!"));//если удачно
            } catch (PlayerNotFoundException | TransactionAlreadyExistsException | JsonParseException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (AuthorizeException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (RuntimeException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(authentication.getMessage()));
        }
    }

    private void requestValidation(TransactionRequest request) throws ValidationParametersException {
        if (request.getPlayerLogin()==null || request.getPlayerLogin().isBlank()) {
            throw new ValidationParametersException("Player login must not be null or empty.");
        } else if (request.getAmount().signum() == -1) {
            throw new ValidationParametersException("Transaction's amount must not be negative.");
        }
    }

    private void indicationsHistoryProcess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws ValidationParametersException, IOException {
        String login = req.getParameter("login");
        if (login == null) throw new ValidationParametersException("Login parameter is null!");
        User user = userService.getByLogin(login);
        if (!authentication.getLogin().equals(user.getName())) throw new AuthorizeException("Incorrect credentials.");
        List<Indications> userHistory = readingService.getUserHistory(user.getId());
        TransactionHistoryResponse response = new TransactionHistoryResponse(user.getName(), indicationsMapper.toDTOList(userHistory));
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), response);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ReadingService getReadingService() {
        return readingService;
    }

    public void setReadingService(ReadingService readingService) {
        this.readingService = readingService;
    }

    public ObjectMapper getJacksonMapper() {
        return jacksonMapper;
    }

    public void setJacksonMapper(ObjectMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }

    public IndicationsMapper getIndicationsMapper() {
        return indicationsMapper;
    }

    public void setIndicationsMapper(IndicationsMapper indicationsMapper) {
        this.indicationsMapper = indicationsMapper;
    }
}
