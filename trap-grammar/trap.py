import argparse
import plot_parser
import json
import pprint
from plot_entity import *



def trap(lines):
	last_level = 1
	last_plot = None
	list = []
	for line in lines:
		temp_level = plot_parser.level(line)
		if temp_level == 1:
			plot = plot_parser.parser(None, line)
			if plot:
				list.append(plot)
				last_plot = plot

		elif temp_level == last_level:
			plot = plot_parser.parser(last_plot.parent, line)	
			if plot:
				list.append(plot)
				last_plot = plot

		elif temp_level > last_level:
			plot = plot_parser.parser(last_plot, line)
			if plot:
				list.append(plot)
				last_plot = plot

		elif temp_level < last_level:
			diff = last_level - temp_level
			temp_parent = last_plot.parent
			for count in range(0, diff):
				temp_parent = temp_parent.parent

			plot = plot_parser.parser(temp_parent, line)
			if plot:
				list.append(plot)
				last_plot = plot

		last_level = temp_level
	
	new_list = []
	for plot in list:
		temp_childs = plot.childs
		new_childs = []
		for temp_child in temp_childs:
			new_childs.append(temp_child.mark)
		plot.childs = new_childs

		if plot.parent:
			plot.parent = plot.parent.mark

	return json.dumps(list, default=lambda o: o.__dict__, ensure_ascii=False, sort_keys=True, indent=4)

def access(text):
	lines = text.split('\n')
	return trap(lines)




