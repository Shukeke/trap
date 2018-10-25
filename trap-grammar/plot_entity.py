class Plot:

	mark = ''

	# 0: 未定义
	# 1: 剧情
	# 2: 剧情选项
	# 3: 跳转
	type = 0

	content = ''

	childs = None

	parent = None

	def tostring(self):
		print(self.mark, self.childs)



class TrapError(Exception):
	def __init__(self, value):
		self.value = value
	def __str__(self):
		return repr(self.value)