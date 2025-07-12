package dev.java10x.movieflix.service;

import dev.java10x.movieflix.entity.Streaming;
import dev.java10x.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {


    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository) {
        this.streamingRepository = streamingRepository;
    }

    public List<Streaming> getAllStreamings() {
        return streamingRepository.findAll();
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(id);
    }

    public Streaming saveStreaming(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public void deleteStream(Long id) {
        streamingRepository.deleteById(id);
    }

}
