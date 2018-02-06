# CTL PLATFORM


## PROJECT DESCRIPTION:

This SDWAN project is built on top of Spring Boot microservices and Angular 2.
Node and npm are installed as part of maven build. Also webpack build and karma tests runs with maven.
It also uses Spring Cloud microservices and Spring Security with Oauth2 JWT tokens.


## MICROSERVICES FOLDER STRUCTURE
```
+---db				        (DB Scripts)
+---docs
+---pctl.mod.address		(Address Microservice)
+---pctl.mod.admin		    (Admin Microservice)
+---pctl.mod.cart		    (Shoppingcart Microservice)
+---pctl.mod.config		    (Config Server)
+---pctl.mod.core		    (Core Module)
+---pctl.mod.discovery		(Discovery Server)
+---pctl.mod.notification	(Notification Server)
+---pctl.mod.order		    (Order Microservice)
+---pctl.mod.product		(Product/Pricing Microservice)
+---pctl.mod.ui			    (SDWAN UI Microservice/ Angular 2)
+---pctl.mod.user		    (User Microservice)
+---scripts			        (Docker Scripts)
+---wsdl
```
## STANDALONE USAGE 

## PRE-REQUISITES
MongoDb service running on default port

### RUNNING IN PRODUCTION MODE:

If you want to work without docker you need to use prod-standalone maven profile

Compile and package project:
```
mvn clean package -P prod
```

Then run discovery server:
```
cd pctl.mod.discovery/
java -jar ./target/ctl-discovery-2.2.0.jar
```

Then run each server:
```
cd pctl.mod.address/
java -jar ./target/pctl.mod.address-1.0.0.jar
```

Then run user server:
```
cd pctl.mod.user/
java -jar ./target/pctl.mod.user-1.0.0.jar
```

Then run product/pricing server:
```
cd pctl.mod.product/
java -jar ./target/pctl.mod.product-1.0.0.jar
```

Then run cart server:
```
cd pctl.mod.cart/
java -jar ./target/pctl.mod.cart-1.0.0.jar
```

Then run ui server:
```
cd pctl.mod.ui/
java -jar ./target/pctl.mod.ui-1.0.0.jar
```


Then run admin server (optional):
```
cd pctl.mod.admin/
java -jar ./target/pctl.mod.admin-1.0.0.jar
```

## UI DEV MODE

To test the UI in dev mode (without microservices and using the mock json-server)

Setup the dev environment
Setup node environment as per https://github.com/ng2-developers/angular2-webpack-starter/blob/master/README.md 

```
npm install
npm run serve
```


### DOCKER BUILD :

Build the docker image for all services with dev maven profile
To use development mode you need also webpack development server running in background

```
mvn install -P prod-docker
```

### RUNNING CONTAINERS:

After building applications run all containers with docker-compose

```
docker-compose up
```