package com.example.jpa.serializer;

import com.example.jpa.domain.Post;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PostSerializer extends JsonSerializer<Post> {

  @Override
  public void serialize(Post post, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("id", post.getId());
    gen.writeStringField("title", post.getTitle());
    gen.writeEndObject();
  }
}
