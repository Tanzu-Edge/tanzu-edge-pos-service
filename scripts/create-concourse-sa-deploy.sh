kubectl create sa concourse
kubectl create clusterrolebinding concourse-default-binding --clusterrole=cluster-admin --serviceaccount=default:concourse
kubectl create clusterrolebinding concourse-ds-binding --clusterrole=cluster-admin --serviceaccount=edge-data-services:concourse
kubectl get secrets
echo "Don't forget to retrieve/set token from concourse service account and set in params.yaml"
