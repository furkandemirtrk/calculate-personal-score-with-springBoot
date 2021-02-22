package com.kocfinans.scoresegment.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kocfinans.scoresegment.exception.ScoreSegmentException;
import com.kocfinans.scoresegment.model.UserDto;
import com.kocfinans.scoresegment.model.UserScoreCalculateRequest;
import com.kocfinans.scoresegment.repository.UserRepository;
import com.kocfinans.scoresegment.service.UserScoreProcessService;
import com.kocfinans.scoresegment.service.UserService;
import com.kocfinans.scoresegment.util.ApiPaths;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest{

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private UserScoreProcessService userScoreProcessService;

  @Mock
  private UserRepository userRepository;

  @Test
  public void userScoreCalculateTest() throws Exception{
    UserDto userDto = UserDto.builder()
        .cityCode(1)
        .monthlyIncomeBracketCode(1)
        .name("Test")
        .surname("Data")
        .phoneNumber("054000000")
        .tcNo("12345678912")
        .scoreSegment(4)
        .transactionDate(LocalDateTime.now()).build();

    UserScoreCalculateRequest userScoreCalculateRequest = UserScoreCalculateRequest.builder()
        .tcNo("12345678912")
        .phoneNumber("054000000")
        .surname("Data")
        .monthlyIncomeBracketCode(1)
        .cityCode(1)
        .name("Test").build();

    when(userService.findUserByTcNo(userScoreCalculateRequest.getTcNo())).thenReturn(userDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiPaths.UserController.CONTROLLER)
        .content(objectMapper.writeValueAsBytes(userScoreCalculateRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult mvcResult = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andReturn();

  }

}
