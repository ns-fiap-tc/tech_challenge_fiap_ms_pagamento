# Remote backend instance, to save tfstate
terraform {
  backend "s3" {
    bucket         = "tc-lanchonete-tfstate-bucket"
    key            = "tech-challenge-app-ms-pagamento/terraform.tfstate"
    region         = "us-east-1"
    dynamodb_table = "terraform-lock"
    encrypt        = true
  }
}
