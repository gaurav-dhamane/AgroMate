from flask import Flask, request, jsonify
import wikipedia

app = Flask(__name__)

@app.route('/wiki-summary')
def wiki_summary():
    query = request.args.get('query')
    if not query:
        return 'Error: No query provided.'
    
    try:
        results = wikipedia.summary(query, sentences=50)
        return results
    except wikipedia.exceptions.PageError:
        return jsonify({'result': 'Sorry, no data available'})



if __name__ == '__main__':
    app.run(debug=True)
