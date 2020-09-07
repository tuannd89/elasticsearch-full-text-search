# ELASTICSEARCH: DEMO

Demo for elasticsearch

## Which are included:

- Gradle
- Spring web
- Spring data jpa
- Mysql
- Elasticsearch

## How to install

- Install gradle (6.x)
- To install and start mysql, elasticsearch, run: docker-compose up -d
- To create new index on elasticsearch if not exists: 

<pre><code>PUT http://127.0.0.1:9200/demo
content-type: application/json
{
  "settings": {
    "number_of_shards": 1
  },
  "mappings": {
    "properties": {
      "id": {
        "type": "integer",
        "index": "false"
      },
      "name": {
        "type": "text"
      }
    }
  }
}
</code></pre>

- Run application
- POST data:
<pre><code>PUT http://127.0.0.1:9200/demo
content-type: application/json
body:
{
  "empName": "Robert Smith"
}
</code></pre>

- To search:

    - GET http://localhost:8080/api/v1/search?keyword=Smith
    - GET http://localhost:8080/api/v1/search?keyword=Smi
