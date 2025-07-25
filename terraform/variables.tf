# AWS provider configuration
variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

# Database pagamento configuration
variable "db_pagamento_username" {
  description = "The username for the RDS pagamento instance"
  type        = string
  sensitive   = true
}

variable "db_pagamento_password" {
  description = "The password for the RDS pagamento instance"
  type        = string
  sensitive   = true
}

variable "db_pagamento_name" {
  description = "Database pagamento name"
  type        = string
  default     = "lanch_cat_db"
}

variable "db_pagamento_port" {
  description = "Database pagamento port"
  type        = string
  default     = "27017"
}

#Variaveis DockerHUB

variable "dockerhub_username" {
  description = "The username of the dockerhub image to deploy"
  type        = string
}

/*variable "dockerhub_token" {
  description = "The access token of the dockerhub image to deploy"
  type        = string
}*/