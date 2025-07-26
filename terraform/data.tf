data "aws_eks_cluster" "lanchonete_cluster" {
  name = "lanchonete_cluster"
}

data "aws_eks_cluster_auth" "lanchonete_cluster_auth" {
  name = data.aws_eks_cluster.lanchonete_cluster.name
}

data "kubernetes_service" "mongodbpag-service" {
  metadata {
    name      = "mongodbpag"
    namespace = "default"
  }
}

data "kubernetes_service" "service-lanchonete-app" {
  metadata {
    name      = "service-lanchonete-app"
    namespace = "default"
  }
}

