#!/bin/bash

set -e

AWS_REGION="us-east-1"
REPO="your-repo-name"
ACCOUNT_ID="your-account-id"
CLUSTER_NAME="your-cluster"
SERVICE_NAME="your-service"
CONTAINER_NAME="your-container"
IMAGE_TAG=$(git rev-parse --short HEAD)
IMAGE_URI="$ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$REPO:$IMAGE_TAG"

echo "Logging into ECR..."
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

echo "Tagging and pushing image..."
docker tag $REPO:latest $IMAGE_URI
docker push $IMAGE_URI

echo "Creating imageDef for ECS..."
echo "[{\"name\":\"$CONTAINER_NAME\",\"imageUri\":\"$IMAGE_URI\"}]" > imagedefinitions.json

echo "Updating ECS service..."
aws ecs update-service \
  --cluster $CLUSTER_NAME \
  --service $SERVICE_NAME \
  --force-new-deployment \
  --region $AWS_REGION
