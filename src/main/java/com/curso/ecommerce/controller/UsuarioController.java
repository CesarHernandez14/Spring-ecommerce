package com.curso.ecommerce.controller;


import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario){
        logger.info("usuario registro : {}",usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }
    
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        logger.info("Accesos : {}",usuario);
        
        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        //logger.info("Usuario de DB: {}",user.get());
        
        if(user.isPresent()){
            session.setAttribute("idusuario", user.get().getId());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }
            else{
                return "redirect:/";
            }
        }else{
            logger.info("Usuario no existe");
        }
        
        
        return "redirect:/";
    }
}
