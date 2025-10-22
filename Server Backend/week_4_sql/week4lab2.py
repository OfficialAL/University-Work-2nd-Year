from flask import Flask, request, jsonify, Response
import sqlalchemy as db
from sqlalchemy import text

app : Flask = Flask(__name__)

# https://docs.sqlalchemy.org/en/20/core/connections.html
db_conn : str = "sqlite:///sql-murder-mystery.db"
engine = db.create_engine(db_conn)

@app.route("/", methods=["GET"])
def hello_world() -> Response:
    return Response("<p>Hello, World!</p>", status=200)

@app.route("/api/v1/person", methods=["GET"])
def person_v1() -> Response:
    # Connecting to the database engine
    with engine.connect() as conn:
        query = text("SELECT * FROM PERSON")
        output = conn.execute(query).fetchall()
        # SQL Alchemy rows cannot be converted directly into json objects
        tuples_result = [tuple(row) for row in output]
        json_result = jsonify(tuples_result)
    return json_result, 200 # returning a JSONified response along with the status code


@app.route("/api/v1/person/<string:id>", methods=["GET"])
def person_byid_v1(id : str) -> Response:
    # Connecting to the database engine
    with engine.connect() as conn:
        query = text(f"SELECT * FROM PERSON WHERE id={id}")
        output = conn.execute(query).fetchall()
        # SQL Alchemy rows cannot be converted directly into json objects
        tuples_result = [tuple(row) for row in output]
        json_result = jsonify(tuples_result)
    return json_result, 200 # returning a JSONified response along with the status code