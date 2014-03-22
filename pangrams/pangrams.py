# https://www.codeeval.com/browse/37/

f = open("input.txt")
for l in f.readlines():
    alphabet = list("abcdefghijklmnopqrstuvwxyz")
    l = l.lower()
    for c in list(l):
        if c in alphabet: alphabet.remove(c)
        if len(alphabet) == 0:
            break

    if len(alphabet) == 0:
        print "NULL"
    else:
        print "".join(alphabet)
