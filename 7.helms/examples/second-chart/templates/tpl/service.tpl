{{- define "service.tpl" }}
apiVersion: v1 
kind: Service
metadata: 
    name: {{ .Values.appName.app }}-svc
spec: 
    type: ClusterIP 
    selector: 
        app: nginx 
    ports: 
    - name: nginx-port 
      port: {{ .Values.container.port }} 
      targetPort: {{ .Values.container.port }}  
{{- end }}