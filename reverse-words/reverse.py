from sys import stdin
import string

def reversewords(line):
    line.reverse()
    print " ".join(line)

map((lambda l: reversewords(string.split(l.strip()))), stdin.readlines())
