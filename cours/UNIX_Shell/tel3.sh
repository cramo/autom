echo Entrez un numéro de téléphone
read -r var
re='^[0-9]+$'
if [ ${#var} -ge 10 ] && [[ $var =~ $re ]]
then
    echo good
else
    echo false
fi

