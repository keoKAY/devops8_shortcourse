


{{- define "sample-deployment" }}
apiVersion: apps/v1
kind: Deployment 
metadata: 
  name: {{ .Values.appName.app }}
spec: 
  replicas: {{ .Values.replicas }}
  selector: 
    matchLabels:     
      # app: nginx-dpl
      {{- toYaml .Values.appName | nindent 6 }} 
  template: 
    metadata: 
      labels: 
        # app: nginx-deployment 
        {{- toYaml .Values.appName | nindent 7 }} 
    spec: 
      containers: 
        {{- with .Values.container }}
        - name: {{ .name }}
          image: {{ .image }}
          env: 
            {{- range .env }}
            - name: {{ .name }}
              value: {{ .value }}
            {{- end }}
          ports: 
          - containerPort: {{ .port }}
        {{- end }}
{{- end }}