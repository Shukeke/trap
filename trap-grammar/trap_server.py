from http.server import HTTPServer, BaseHTTPRequestHandler
import json
import trap

data = {'result': 'this is a test'}
host = ('localhost', 8888)

class Resquest(BaseHTTPRequestHandler):
	def do_GET(self):
		self.send_response(200)
		self.send_header('Content-type', 'application/json')
		self.end_headers()
		self.wfile.write(json.dumps(data).encode())

	def do_POST(self):
		datas = self.rfile.read(int(self.headers['content-length']))
		game_text = str(datas, "utf-8")
		result = trap.access(game_text)
		self.send_response(200)
		self.send_header('Content-type', 'application/json;charset=utf-8')
		self.end_headers()
		self.wfile.write(bytes(result, encoding='utf-8'))
		
		

if __name__ == '__main__':
	server = HTTPServer(host, Resquest)
	print("Starting server, listen at: %s:%s" % host)
	server.serve_forever()