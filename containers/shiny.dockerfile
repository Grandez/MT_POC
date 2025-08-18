# Imagen base con R y Shiny preinstalado
FROM rocker/shiny:4.3.2

# Instala librerías adicionales de R si las necesitas
# RUN R -e "install.packages(c('dplyr','ggplot2'), repos='https://cloud.r-project.org/')"

# Copia la app al directorio del servidor Shiny
COPY ./ /srv/shiny-server/miApp

# Expone el puerto 3838 (por defecto de Shiny Server)
EXPOSE 3838

# Arranca Shiny Server
CMD ["/usr/bin/shiny-server"]
