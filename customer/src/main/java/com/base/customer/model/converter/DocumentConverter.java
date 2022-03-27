package com.base.customer.model.converter;

import org.bson.Document;

import java.util.List;

public interface DocumentConverter<T> {
    List<T> fromBsonDocuments(List<Document> bsonDocuments);

    Object fromDocument(Document bsonDocument, Class<T> mongoDocumentClass);
}
