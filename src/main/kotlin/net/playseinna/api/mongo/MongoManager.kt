package net.playseinna.api.mongo

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

open class MongoManager(database: String) {

    var mongoClient: MongoClient = MongoClient(MongoClientURI("${System.getenv("MONGO")}$database?retryWrites=true&w=majority"))
    var database: MongoDatabase = mongoClient.getDatabase(database)

    private val cachedCollections = ArrayList<MongoCollection<Document>>()

    fun get(collection: String, key: String, value: Any): Document? {
        return getCollection(collection).find(BasicDBObject(key, value)).first()
    }

    fun insert(collection: String, document: Document) {
        getCollection(collection).insertOne(document)
    }

    fun update(collection: String, key:String, value: Any, updatedField: Document) {
        getCollection(collection).updateOne(BasicDBObject(key, value), BasicDBObject("\$set", updatedField))
    }

    fun delete(collection: String, document: Document) {
        getCollection(collection).deleteOne(document)
    }

    private fun getCollection(collection: String): MongoCollection<Document> {
        for (c in cachedCollections) {
            if (c.namespace.collectionName == collection) {
                return c
            }
        }
        val c = database.getCollection(collection)
        cachedCollections.add(c)
        return c
    }

}