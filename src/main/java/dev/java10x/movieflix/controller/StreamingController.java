package dev.java10x.movieflix.controller;

import dev.java10x.movieflix.controller.request.StreamingRequest;
import dev.java10x.movieflix.controller.response.StreamingResponse;
import dev.java10x.movieflix.entity.Streaming;
import dev.java10x.movieflix.mapper.StreamingMapper;
import dev.java10x.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {


    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> listAll() {
        List<StreamingResponse> list = streamingService.getAllStreamings().stream()
                .map(StreamingMapper::toStreamingResponse).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(category -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> createStreaming(@RequestBody StreamingRequest request) {
        Streaming newStreaming = streamingService.saveStreaming(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(newStreaming));

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id) {
        streamingService.deleteStream(id);
        return ResponseEntity.noContent().build();
    }

}
