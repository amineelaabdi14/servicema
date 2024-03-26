package com.youcode.servicema.comment;

import com.youcode.servicema.domain.entities.Category;
import com.youcode.servicema.domain.entities.Comment;
import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.CommentRepository;
import com.youcode.servicema.repositories.ServiceRepository;
import com.youcode.servicema.services.ServiceService;
import com.youcode.servicema.services.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class CommentServiceImplTest {
    private CommentServiceImpl commentServiceImpl;
    private CommentRepository commentRepository = Mockito.mock(CommentRepository.class);
    private ServiceService serviceService= Mockito.mock(ServiceService.class);
    @BeforeEach
    public void init() {
        commentServiceImpl = new CommentServiceImpl( serviceService,commentRepository);
    }
    @Test
    public void testSave() {
//        Service service =new Service();
        when(serviceService.getServiceById(1L)).thenReturn(Optional.ofNullable(null));
        assertThrows(CustomException.class, () -> commentServiceImpl.save(1L,"comment"));
    }
    @Test
    public void testSave2() {
        User user = User.builder()
                .id(1L)
                .build();
        Service service = Service.builder()
                .user(user)
                .build();
        when(serviceService.getServiceById(1L)).thenReturn(Optional.of(service));
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        assertThrows(CustomException.class, () -> commentServiceImpl.save(1L,"comment"));
    }

}
