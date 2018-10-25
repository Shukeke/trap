from plot_entity import *
import uuid

global plot_flag
global plot_choice
global plot_jump
global plot_anchor
global plot_anchor_left
global plot_anchor_right

global type_of_plot
global type_of_choice

plot_flag = '$'
plot_choice = '@'
plot_jump = '>'
plot_anchor_left = '['
plot_anchor_right = ']'

type_of_plot = 1
type_of_choice = 2
type_of_jump = 3

def parser(parent, line):
	if line.strip():

		line = ltrim(line)

		temp_mark = str(uuid.uuid1())
		temp_type = 0
		temp_content = ''

		if line.startswith(plot_flag):
			temp_type = type_of_plot
			
		elif line.startswith(plot_choice):
			temp_type= type_of_choice

		elif line.startswith(plot_jump):
			temp_type = type_of_jump

		if line.startswith(plot_anchor_left, 1):
				left_index = line.find(plot_anchor_left)
				right_index = line.find(plot_anchor_right)
				if right_index != -1:
					anchor_content = line[left_index + 1:right_index]
					temp_mark = anchor_content
					temp_content = ltrim(line[right_index + 1:])
				else:
					raise TrapError('Not found right anchor!')
		else:
			temp_content = ltrim(line[1:])


		plot = Plot()
		plot.mark = temp_mark
		plot.type = temp_type
		plot.content = temp_content.rstrip()
		plot.childs = []
		plot.parent = None

		if parent:
			plot.parent = parent
			parent.childs.append(plot)

		return plot
	else:
		return None


def ltrim(line):
	line = line.lstrip()
	return line

def level(line):
	count = 1
	for c in line:
		if c == '\t':
			count = count + 1
		elif c == ' ':
			continue
		else:
			break
	return count
