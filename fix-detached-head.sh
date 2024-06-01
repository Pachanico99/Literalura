#!/bin/bash 
#Verificar el estado actual 
echo "Verificando el estado actual..." 
git status

# Añadir cambios a la zona de staging 
echo "Añadiendo cambios a la zona de staging..." 
git add .


# Hacer commit de los cambios 
echo "Haciendo commit de los cambios..." 
git commit -m "Commit temporal en estado detached"


# Crear una nueva rama desde el estado detached 
echo "Creando una nueva rama temporal..." 
git checkout -b mi-rama-temporal


# Cambiar a la rama master 
echo "Cambiando a la rama master..." 
git checkout master


# Fusionar los cambios de la nueva rama temporal 
echo "Fusionando los cambios de la rama temporal en master..." 
git merge mi-rama-temporal


# Eliminar la rama temporal (opcional) 
echo "Eliminando la rama temporal..." 
git branch -d mi-rama-temporal

# Subir los cambios al repositorio remoto 
echo "Subiendo los cambios al repositorio remoto..." 
git push origin master

echo "Proceso completado."

