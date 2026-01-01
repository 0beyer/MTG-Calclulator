import fileinput

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

def fillDeck(file):
    deck = []
    c=0
    for line in fileinput.input(files=file):
        z = line.split("; ")
        x = z[1].rstrip("\n").split(", ")
        y = int(z[0])
        deck.append(Card(x,y))
        # print(deck[c].atr)
        # print(deck[c].count)
        c+=1
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