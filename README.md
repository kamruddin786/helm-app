# helm-app
Spring boot application with PostgreSQL and deploy using helm

## Overview

This is a Spring Boot application that uses PostgreSQL as its database. It is designed to be deployed on Kubernetes using Helm charts.

## Prerequisites

-   Kubernetes cluster
-   Helm installed

## Deployment

To deploy the application using Helm, follow these steps:

1.  **Clone the repository:**

    ```shell
    git clone <repository-url>
    cd helm-app
    ```

2.  **Install the Helm chart:**

    ```shell
    helm install <app_name> ./<dir_containing_helm_chart>
    helm install helm-app ./helm-app
    ```

    This command will deploy the application to your Kubernetes cluster.

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
