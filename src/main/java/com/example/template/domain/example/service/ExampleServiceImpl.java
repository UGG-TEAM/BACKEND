package com.example.template.domain.example.service;

import com.example.template.domain.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

}
