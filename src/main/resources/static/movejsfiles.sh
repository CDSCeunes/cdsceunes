#!/bin/bash

for FILE in `find . -name "*.js" -type f -o -path './vendor' -prune -o -path './components' -prune`
do
    if [ -e $FILE ] ; then
        EXT=".js"
        COFFEE="$FILE"

        #echo "converting ${FILE} to ${COFFEE}"
        echo $COFFEE
        #js2coffee "$FILE" > "$COFFEE"
        mv "$FILE" "old/.$FILE"
    else
        echo "File: {$1} does not exist!"
    fi
done
