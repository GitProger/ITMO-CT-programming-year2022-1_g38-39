'''#6
print(
' ''\u002a\u002f\u0077\u0068\u0069\u006c\u0065\u0020\u0028\u0022\u0031\u0022
\u002e\u006c\u0065\u006e\u0067\u0074\u0068\u0028\u0029\u0020\u003d\u003d
\u0020\u0031\u0029\u003b\u0020\u0020\u006c\u006f\u006e\u0067\u0020\u0009
\u0020\u0020\u0072\u0065\u0073\u0075\u006c\u0074\u0020\u003d\u0020\u000a
\u0034\u0030\u0033\u0039\u0032\u0038\u0034\u0030\u0039\u0033\u0038\u004c
\u002b\u0070\u0061\u0073\u0073\u0077\u006f\u0072\u0064\u005b\u0033\u005d
\u003b\u002f\u002a
' '')
'''

'''#8
from PIL import Image
im = Image.open('oralogo_small.gif')
pixels = im.convert('RGB')
width, height = im.size
red = (255, 0, 0)

r = 0
all_pixels = []
for x in range(width):
    for y in range(height):
        p = pixels.getpixel((x, y))
        if p == red:
            r += 1

print(r)
'''
import regex as re
#ctrl+a -> ctrl+c -> ctrl+v to key7.txt

ws = " ".join(open('key7.txt').readlines()).split()
d = {}
for word in ws:
    word = word.lower()
    if not word[-1].isalpha():
        word = word[-1]
    if not word.isalpha():
        continue
    if d.get(word) == None:
        d[word] = 1
    else:
        d[word] += 1

for e in sorted(d.keys(), reverse=True, key=lambda x: d[x]):
    print(e, d[e])

print(d['name'], d['names'], d['name'] + d['names'])

''''
name 232
type 221
class 304
'''

# /\bnames?\b/gi