#!/usr/bin/python
''' Сохранить в файл XML-документ, расположенный по
ссылке https://msdn.microsoft.com/ruru/library/ms762271(v=vs.85).aspx.
Для каждой книги вывести на экран ее идентификатор, название
и автора (допускается использование как SAXparser,
так и DOM-parser)'''

import xml.sax

class CorpusHandler(xml.sax.ContentHandler):
    def __init__(self):
        self.cdata = ''
        self.cword = 0
        self.words = []
        self.title = ''
        self.author = ''

    def startElement(self, tag, attributes):
        self.cdata = tag
        if tag == 'book':
            print("Book id: " + attributes['id'])

    def characters(self, content):
        if self.cdata == 'title':
            self.title = content
        if self.cdata == 'author':
            self.author = content

    def endElement(self, tag):

        if self.cdata == 'title':
            print("Title: " + self.title + "\n")

        if self.cdata == 'author':
            print("Author: " + self.author)

        self.cdata = ''

file_name = "hw112xml.xml"

parser = xml.sax.make_parser()
Handler = CorpusHandler()
parser.setContentHandler(Handler)
parser.parse(file_name)



