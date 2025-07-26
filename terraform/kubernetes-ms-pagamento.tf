resource "kubernetes_secret" "secrets-pagamento" {
  metadata {
    name = "secrets-pagamento"
  }

  type = "Opaque"

  data = {
    DB_HOST             = data.kubernetes_service.mongodbpag-service.metadata[0].name
    DB_PORT             = var.db_pagamento_port
    DB_NAME             = var.db_pagamento_name
    DB_PAGAMENTO_USERNAME = var.db_pagamento_username
    DB_PAGAMENTO_PASSWORD = var.db_pagamento_password
    PAGAMENTO_MOCK_HOST = kubernetes_service.service-pagamento-mock.metadata[0].name
    PEDIDO_SERVICE_HOST = data.kubernetes_service.service-lanchonete-app.metadata[0].name
  }

  lifecycle {
    prevent_destroy = false
  }
}

# MS PAGAMENTO 
resource "kubernetes_deployment" "deployment-ms-pagamento" {
  metadata {
    name      = "deployment-ms-pagamento"
    namespace = "default"
  }

  spec {
    selector {
      match_labels = {
        app = "deployment-ms-pagamento"
      }
    }

    template {
      metadata {
        labels = {
          app = "deployment-ms-pagamento"
        }
      }

      spec {
        toleration {
          key      = "key"
          operator = "Equal"
          value    = "value"
          effect   = "NoSchedule"
        }

        container {
          name  = "deployment-ms-pagamento-container"
          image = "${var.dockerhub_username}/fiap-tech-challenge-lanchonete-ms-pagamento:latest"

          resources {
            requests = {
              memory : "512Mi"
              cpu : "500m"
            }
            limits = {
              memory = "1Gi"
              cpu    = "1"
            }
          }

          env_from {
            secret_ref {
              name = kubernetes_secret.secrets-pagamento.metadata[0].name
            }
          }

          port {
            container_port = "8080"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "service-ms-pagamento" {
  metadata {
    name      = "service-ms-pagamento"
    namespace = "default"
    annotations = {
      "service.beta.kubernetes.io/aws-load-balancer-type" : "nlb",
      "service.beta.kubernetes.io/aws-load-balancer-scheme" : "internal",
      "service.beta.kubernetes.io/aws-load-balancer-cross-zone-load-balancing-enabled" : "true"
    }
  }
  spec {
    selector = {
      app = "deployment-ms-pagamento"
    }
    port {
      port = "80"
      target_port = "8080"
      node_port = "30003"
    }
    type = "LoadBalancer"
  }
}
