//package config;
//
//import com.example.PlaceAdminister.Repository.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//    private final JwtUtils jwtUtils;
//    private final UserRepository repository;
//
//    @Override
//    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response
//                                    @NotNull FilterChain chain)throws ServletException, IOException{
//        String authMeader=request.getHeader("Authorization");
//        String username=null;
//        Long userName=null;
//        String jwt = null;
//
//        if(authMeader != null && authMeader.startsWith("Bearer")){
//            jwt = authMeader.substring(7);
//            username = jwtUtils.extractUsername(jwt);
//        }
//        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails= this.repository.findByUserName(username);
//        }
//    }
//
//}
