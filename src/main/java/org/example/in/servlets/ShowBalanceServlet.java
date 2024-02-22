package org.example.in.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.in.mappers.UserMapper;
import org.example.model.User;
import org.example.service.UserService;
import org.example.exception.AuthorizeException;
import org.example.exception.PlayerNotFoundException;
import org.example.exception.ValidationParametersException;
import org.example.in.dto.ExceptionResponse;
import org.example.in.security.Authentication;

import java.io.IOException;

@WebServlet("/players/balance")
public class ShowBalanceServlet extends HttpServlet {
    private UserService userService;
    private ObjectMapper jacksonMapper;
    private UserMapper userMapper;
    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
        userMapper = (UserMapper) getServletContext().getAttribute("userMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try {
                showBalanceProcess(req, resp, authentication);
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

    private void showBalanceProcess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws ValidationParametersException, IOException {
        String login = req.getParameter("login");
        if (login == null) throw new ValidationParametersException("Login parameter is null!");
        User entity = userService.getByLogin(login);
        if (!authentication.getLogin().equals(entity.getName())) throw new AuthorizeException("Incorrect credentials.");
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), userMapper.toDto(entity));
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ObjectMapper getJacksonMapper() {
        return jacksonMapper;
    }

    public void setJacksonMapper(ObjectMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
