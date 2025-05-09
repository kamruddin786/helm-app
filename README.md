# helm-app
Spring boot application with PostgreSQL and deploy using helm

## Overview

This is a Spring Boot application that uses PostgreSQL as its database. It is designed to be deployed on Kubernetes using Helm charts.

## Prerequisites

-   Kubernetes cluster
-   Helm installed

## Deployment

To deploy the application using Helm, follow these steps:

1. **Clone the repository:**

    ```shell
    git clone <repository-url>
    cd helm-app
    ```

2. **Build and Push Docker Image to Docker Hub:**

    Replace `<docker-hub-username>` and `<image-name>` with your Docker Hub username and preferred image name.

    ```shell
    # Build the Docker image
    docker build -t <docker-hub-username>/<image-name>:<tag> .

    # Push the Docker image to Docker Hub
    docker push <docker-hub-username>/<image-name>:<tag>
    ```

3. **Update Docker Image in Helm Values:**

    Edit the `values.yaml` file in your Helm chart to update the Docker image with the new image name and tag:

    ```yaml
    image:
      repository: <docker-hub-username>/<image-name>
      tag: <tag>
    ```

4. **Install the Helm chart:**

    ```shell
    helm install <app_name> ./<dir_containing_helm_chart>
    helm install helm-app ./helm-app
    ```

    This command will deploy the application to your Kubernetes cluster.

5. **Remove the deployment:**

    ```shell
    helm delete <app_name>
    ```

## Why Separate Database Service?

In this setup, the PostgreSQL database is deployed as a separate service for several reasons:

-   **Scalability:** Separating the database allows it to be scaled independently of the application. This is useful if the database needs more resources than the application, or vice versa.
-   **Reusability:** By deploying the database as a separate service, it can be reused by other applications in the cluster.
-   **Maintainability:** Separating the database makes it easier to maintain and update. For example, you can upgrade the database without affecting the application.
-   **Decoupling:** Decoupling the database from the application improves the overall architecture by reducing dependencies and increasing flexibility.
Initially we kept the postgres container as a part of main deployment, but we faced issues while resolving the database connection. So we decided to keep it as a separate service.

## Configuration

The Helm chart can be configured using the `values.yaml` file. This file contains the default values for the chart. You can override these values by creating a custom `values.yaml` file and passing it to the `helm install` command:

```shell
helm install my-app . -f custom-values.yaml
```

## ConfigMap
We are not using configMap in this project. But if you want to use uncomment the configMap.yaml file and add the required properties in the configMap.yaml file. 
Then you can use it in your application by using @Value annotation.

## Local deployment

### Using Minikube
For a local Kubernetes cluster running on Minikube, you can use the following command to access the application:
```shell
   minikube service <service-name> --url
```
Use the displayed URL to access the application.

### Using Docker Desktop
If you are using Docker Desktop, you need to create a NodePort service to access the application.
```yaml
   apiVersion: v1
   kind: Service
   metadata:
     name: helm-app-service
     labels:
       app: helm-app
   spec:
     type: NodePort
     selector:
       app: helm-app
     ports:
       - port: 9090       # The port your application listens on, refer to your application.properties
         targetPort: 9090 # The container port, refer to your values.yaml
         nodePort: 30007  # The NodePort to expose (must be in the range 30000-32767)
  ```

Save this configuration in a file (e.g., nodeport-service.yaml) and apply it using:
```shell
   kubectl apply -f nodeport-service.yaml
```
This will create a NodePort service that exposes your application on port 30007.
You can then access your application at localhost or 127.0.0.1 with port 30007

