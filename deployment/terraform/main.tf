terraform {
  backend "pg" {
  }
}

provider "heroku" {
}

variable "product_app_name" {
  description = "Unique name of the Product app"
}

variable "shopping_app_name" {
  description = "Unique name of the Shopping app"
}

variable "report_app_name" {
  description = "Unique name of the Report app"
}

variable "gateway_app_name" {
  description = "Unique name of the Gateway app"
}

resource "heroku_config" "prod" {
  vars = {
    STAGE = "PROD"
  }
}

resource "heroku_app" "product-service" {
  name   = var.product_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "product-service" {
  app = heroku_app.product-service.id

  source {
    path = "products"
  }
}

resource "heroku_app_config_association" "product-service" {
  app_id = heroku_app.product-service.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon" "database" {
  app  = heroku_app.product-service.name
  plan = "heroku-postgresql:hobby-dev"
}

resource "heroku_app" "shopping-service" {
  name   = var.shopping_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "shopping-service" {
  app = heroku_app.shopping-service.id

  source {
    path = "shopping"
  }
}

resource "heroku_app_config_association" "shopping-service" {
  app_id = heroku_app.shopping-service.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon_attachment" "database" {
  app_id  = heroku_app.shopping-service.id
  addon_id = heroku_addon.database.id
}

resource "heroku_app" "report-service" {
  name   = var.report_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "report-service" {
  app = heroku_app.report-service.id

  source {
    path = "report"
  }
}

resource "heroku_app_config_association" "report-service" {
  app_id = heroku_app.report-service.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon_attachment" "database1" {
  app_id  = heroku_app.report-service.id
  addon_id = heroku_addon.database.id
}

resource "heroku_app" "gateway-service" {
  name   = var.gateway_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "gateway-service" {
  app = heroku_app.gateway-service.id

  source {
    path = "gateway"
  }
}

resource "heroku_app_config_association" "gateway-service" {
  app_id = heroku_app.gateway-service.id

  vars = heroku_config.prod.vars
}

output "product_app_url" {
  value = "http://${heroku_app.product-service.name}.herokuapp.com"
}
output "shopping_app_url" {
  value = "http://${heroku_app.shopping-service.name}.herokuapp.com"
}
output "report_app_url" {
  value = "http://${heroku_app.report-service.name}.herokuapp.com"
}
output "gateway_app_url" {
  value = "http://${heroku_app.gateway-service.name}.herokuapp.com"
}
