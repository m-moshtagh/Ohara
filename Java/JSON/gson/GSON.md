# GSON is a library to serialize Java Objects to JSON and vice versa.

## Three ways of processing JSON:

* Streaming API: JsonReader & JsonWriter read/write data as JsonToken. this way has the lowest overhead, and it's fast.
* Tree model: This approach builds a tree of Json object nodes.
* Data Binding: It converts Json to and from POJOs.

## Steps to use GSON:

* create GSON object using GsonBuilder.
* We can use fromJson to deserialize json to Object.
* We can use toJson to serialize Object to json.