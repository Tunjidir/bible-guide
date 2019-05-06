# Build
mvn clean package && docker build -t com.olatunji/reading-plan .

# RUN

docker rm -f reading-plan || true && docker run -d -p 8080:8080 -p 4848:4848 --name reading-plan com.olatunji/reading-plan 