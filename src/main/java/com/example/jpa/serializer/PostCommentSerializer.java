package com.example.jpa.serializer;

import com.example.jpa.domain.PostComment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PostCommentSerializer extends JsonSerializer<PostComment> {
  @Override
  public void serialize(PostComment postComment, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("id", postComment.getId());
    gen.writeEndObject();
  }
}
