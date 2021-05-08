kubectl -n concourse-main create sa concourse
kubectl create clusterrolebinding concourse-main-binding --clusterrole=cluster-admin --serviceaccount=concourse-main:concourse
kubectl create clusterrolebinding concourse-default-binding --clusterrole=cluster-admin --serviceaccount=default:concourse
kubectl create clusterrolebinding concourse-binding --clusterrole=cluster-admin --serviceaccount=concourse:concourse
kubectl -n concourse-main get secrets
echo "Don't forget to retrieve/set token from concourse service account and set in params.yaml"
