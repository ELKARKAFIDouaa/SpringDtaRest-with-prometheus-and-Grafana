# Étape 1 : Build du front
FROM node:20 AS build
WORKDIR /app
COPY src/main/webapp/reactjs/package*.json ./
RUN npm install
COPY src/main/webapp/reactjs/ .
RUN npm run build

# Étape 2 : Servir le front avec Nginx
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
