# ==========================================
# STAGE 1: Build Environment
# ==========================================
FROM node:20-alpine AS build

# Imposta la directory di lavoro all'interno della cartella 'fe/'
WORKDIR /app

# Copia solo i file delle dipendenze per sfruttare il caching di Docker
COPY package*.json ./

# Installa le dipendenze (usa 'npm ci' per build riproducibili in CI)
RUN npm ci

# Copia tutto il resto del codice sorgente del frontend
COPY . .

# Esegue la build di produzione (Angular CLI genera i file in /app/dist/ browser)
RUN npm run build -- --configuration=production

# ==========================================
# STAGE 2: Production Environment (Nginx)
# ==========================================
FROM nginx:1.25-alpine

# Rimuovi la configurazione predefinita di Nginx
RUN rm /etc/nginx/conf.d/default.conf

# Copia la nostra configurazione Nginx ottimizzata (vedi sotto)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia i file statici generati dalla build Angular.
# IMPORTANTE: Sostituisci '<NOME-PROGETTO-ANGULAR>' con il valore 'projects -> architecture -> outputPath' del tuo angular.json
COPY --from=build /app/dist/sonetto-fe/browser /usr/share/nginx/html

# Esponi la porta 80 (standard per Railway/Cloud)
EXPOSE 80

# Avvia Nginx in primo piano (necessario per Docker)
CMD ["nginx", "-g", "daemon off;"]