import fileinput
from PyQt6.QtWidgets import QApplication, QWidget, QTextEdit, QComboBox, QPushButton, QLabel, QHBoxLayout, QVBoxLayout

class Card:
    def __init__(self, atr, count):
        self.atr = atr
        self.count = count
    
    def containsAtr(self, atrCheck):
        for s in self.atr:
            if atrCheck == s:
                return True
        return False            
    
f = 'Riemann Deck.text'

class App(QWidget):

    def __init__(self):
        super().__init__()
        self.initUI()        
        
    def initUI(self):
        self.input_box = QTextEdit()
        self.output_box = QTextEdit()
        self.reverse = QPushButton("Reverse")
        self.reset = QPushButton("Reset")
        self.submit = QPushButton("Translate Now")
        self.input_option = QComboBox()
        self.output_option = QComboBox()
        self.title = QLabel("PyLate")
        
        self.master = QHBoxLayout()
        col1 = QVBoxLayout()
        col2 = QVBoxLayout()
        
        col1.addWidget(self.title)
        col1.addWidget(self.input_option)
        col1.addWidget(self.output_option)
        col1.addWidget(self.submit)
        col1.addWidget(self.reset)
        
        col2.addWidget(self.input_box)
        col2.addWidget(self.reverse)
        col2.addWidget(self.output_box)
        
        self.master.addLayout(col1,20)
        self.master.addLayout(col2, 80)
        self.setLayout(self.master)
        
        
        self.setStyleSheet("""
            QWidget {
                background-color: #333; /* Darker background color */
                color: #fff; /* Text color */
            }

            QPushButton {
                background-color: #66a3ff; /* Lighter background color for buttons */
                color: #333; /* Text color for buttons */
                border: 1px solid #fff; /* White border for buttons */
                border-radius: 5px; /* Rounded corners for buttons */
                padding: 5px 10px; /* Padding for buttons */
            }

            QPushButton:hover {
                background-color: #3399ff; /* Lighter background color for buttons on hover */
            }
        """)

    def fillDeck(file):
        deck = []
        
        for line in fileinput.input(files=file):
            z = line.split("; ")
            x = z[1].rstrip("\n").split(", ")
            y = int(z[0])
            deck.append(Card(x,y))
        return deck

    def atrCount(deck, s):
        c = 0
        for card in deck:
            if card.containsAtr(s):
                c+=card.count
        return c

    def cardCount(deck):
        c = 0
        for card in deck:
            c += card.count
        return c

    def atrList(deck):
        atrNew = []
        for card in deck:
            for string in card.atr:
                atrNew.append(string)
        return atrNew

    def atrStat(deck, attribute, drawTop, attributeCount):
    
        percNot = 1

        for s in attribute:
            c = atrCount(deck, s)
            l = cardCount(deck)
            for i in range(drawTop):
                percNot *= (l-(c/attributeCount)) / l
                l -= 1

        percDec = 1-percNot
        per = str((round(percDec,4)*100))

        return per + "%"

    def main(file, a):
        deck = fillDeck(file)
        print("Draw Chance for " + str(a) + ": " + atrStat(deck, a, 7, 1))

    main(f, ['Land'])

if __name__ in "__main__":
    app = QApplication([])
    main = App()
    main.show()
    app.exec_()