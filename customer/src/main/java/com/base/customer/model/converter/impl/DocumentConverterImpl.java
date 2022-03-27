package com.base.customer.model.converter.impl;

import com.base.customer.model.converter.DocumentConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class DocumentConverterImpl<T> implements DocumentConverter<T> {

    private final Type listType = (new TypeToken<List<T>>() {
    }).getType();
    private Gson gsonMapper = (new GsonBuilder()).create();

    @Override
    public List<T> fromBsonDocuments(List<Document> bsonDocuments) {
        return (List)this.gsonMapper.fromJson(this.gsonMapper.toJson(bsonDocuments), this.listType);
    }

    @Override
    public T fromDocument(Document bsonDocument, Class<T> mongoDocumentClass) {
        return this.gsonMapper.fromJson(this.gsonMapper.toJson(bsonDocument), mongoDocumentClass);
    }
}
