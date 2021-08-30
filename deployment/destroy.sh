echo "Running destroy.sh"
export ALL_HEROKU_APPS=$(heroku apps)
echo $ALL_HEROKU_APPS
echo $TERRAFORM_PG_BACKEND
if [[ $ALL_HEROKU_APPS =~ "$TERRAFORM_PG_BACKEND" ]];
then
 	echo "It's there!"
else 
 	echo "It's no there!"
	heroku create $TERRAFORM_PG_BACKEND
	heroku addons:create heroku-postgresql:hobby-dev --app $TERRAFORM_PG_BACKEND
fi

cd terraform || exit
DATABASE_URL=$(heroku config:get DATABASE_URL --app "$TERRAFORM_PG_BACKEND") && export DATABASE_URL

terraform init -backend-config="conn_str=$DATABASE_URL"

echo "Connected to database"

terraform destroy -auto-approve -var product_app_name="$APP_NAME_AGENT_PRODUCTS" \
                              -var gateway_app_name="$APP_NAME_AGENT_GATEWAY" \
                              -var shopping_app_name="$APP_NAME_AGENT_SHOPPING" \
                              -var report_app_name="$APP_NAME_AGENT_REPORT"
                              