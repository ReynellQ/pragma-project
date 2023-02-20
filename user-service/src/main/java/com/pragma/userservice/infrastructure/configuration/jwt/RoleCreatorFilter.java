package com.pragma.userservice.infrastructure.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.infrastructure.exception.ForbiddenCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;

@Component
public class RoleCreatorFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public RoleCreatorFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserRegister userRegister;
        String jwt;
        userRegister = mapper.readValue(request.getInputStream(), UserRegister.class);
        jwt = request.getHeader("Authorization").substring(7);;
        Integer idRole = jwtService.extractRole(jwt);
        if(!hasPermissionToCreate(idRole, userRegister.getIdRole())){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter()
                    .print(mapper.writeValueAsString(Map.of(
                            "message", "You are not allowed to created a user with this role."
                    )));

            return;
        }
        filterChain.doFilter(request, response);
    }
    private boolean hasPermissionToCreate(Integer creatorRole, Integer newUserRole){
        return rolesWithAutorizationToSave().get(creatorRole).contains(newUserRole);
    }
    private Map<Integer, Set<Integer>> rolesWithAutorizationToSave(){
        return Map.of(
                1, new HashSet<>(List.of(2)),
                2, new HashSet<>(List.of(3))
        );
    }


}
