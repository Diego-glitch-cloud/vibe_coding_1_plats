# TuneChat - Aplicación de Recomendaciones Musicales 🎵

## Descripción
TuneChat es una aplicación Android nativa que utiliza la API de OpenAI para proporcionar recomendaciones musicales personalizadas mediante un chat interactivo.

## Características
- Chat en tiempo real con IA especializada en música
- Interfaz moderna con Jetpack Compose
- Diseño en colores morado y blanco
- Arquitectura MVVM limpia y modular
- Integración con OpenAI API

## Estructura del Proyecto

```
app/src/main/java/com/diegocal/tunechat/
├── MainActivity.kt                      # Activity principal
├── model/
│   └── MusicMessage.kt                 # Modelos de datos del chat
├── data/
│   ├── network/
│   │   ├── OpenAIModels.kt            # Modelos de datos API
│   │   ├── OpenAIService.kt           # Interfaz Retrofit
│   │   └── RetrofitClient.kt          # Cliente HTTP configurado
│   └── repository/
│       └── ChatRepository.kt          # Capa de datos
├── ui/
│   ├── screens/
│   │   └── ChatScreen.kt              # Pantalla principal del chat
│   ├── viewmodel/
│   │   └── ChatViewModel.kt           # Lógica de presentación
│   └── theme/
│       ├── Color.kt                   # Colores personalizados
│       ├── Theme.kt                   # Tema de la app
│       └── Type.kt                    # Tipografía
```

## Dependencias Incluidas

### Core Android
- AndroidX Core KTX
- Lifecycle Runtime KTX
- Activity Compose

### Jetpack Compose
- Compose BOM 2024.09.00
- Material3
- UI Tooling

### Networking
- Retrofit 2.11.0
- Gson Converter
- OkHttp Logging Interceptor

### Coroutines
- Kotlinx Coroutines Android 1.9.0
- Kotlinx Coroutines Core 1.9.0

## Configuración Inicial

### 1. Obtener API Key de OpenAI

1. Visita [OpenAI Platform](https://platform.openai.com/)
2. Crea una cuenta o inicia sesión
3. Ve a **API Keys** en tu perfil
4. Genera una nueva API Key
5. **Guarda la key en un lugar seguro** (solo se muestra una vez)

### 2. Configurar la API Key en el Proyecto

**Opción A: Desarrollo (No recomendada para producción)**

Edita `app/build.gradle.kts` y reemplaza la línea:
```kotlin
buildConfigField("String", "OPENAI_API_KEY", "\"\"")
```

Por:
```kotlin
buildConfigField("String", "OPENAI_API_KEY", "\"tu-api-key-aqui\"")
```

**Opción B: Producción (Recomendada)**

1. Crea un archivo `local.properties` en la raíz del proyecto (si no existe)
2. Agrega tu API key:
```properties
OPENAI_API_KEY=tu-api-key-aqui
```

3. Modifica `app/build.gradle.kts`:
```kotlin
import java.util.Properties

android {
    // ... código existente ...
    
    defaultConfig {
        // ... código existente ...
        
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        val apiKey = properties.getProperty("OPENAI_API_KEY", "")
        buildConfigField("String", "OPENAI_API_KEY", "\"$apiKey\"")
    }
}
```

4. Asegúrate de que `local.properties` esté en `.gitignore` (ya está incluido por defecto)

### 3. Sync y Build

1. Abre el proyecto en Android Studio
2. Click en **File > Sync Project with Gradle Files**
3. Espera a que se descarguen todas las dependencias
4. Click en **Build > Make Project**

### 4. Ejecutar la Aplicación

1. Conecta un dispositivo Android (API 34+) o inicia un emulador
2. Click en el botón **Run** (▶️) o presiona `Shift + F10`
3. La aplicación se instalará y ejecutará

## Uso de la Aplicación

1. Al abrir la app, verás un mensaje de bienvenida de TuneChat
2. Escribe tu consulta en el campo de texto inferior (ej: "Recomiéndame música relajante")
3. Presiona el botón de enviar (ícono de flecha)
4. Espera la respuesta de la IA con recomendaciones personalizadas
5. Continúa la conversación para refinar tus recomendaciones

## Características Técnicas Implementadas

### ✅ Arquitectura MVVM
- **Model**: `MusicMessage`, `OpenAIModels`
- **View**: `ChatScreen`, Composables UI
- **ViewModel**: `ChatViewModel` con StateFlow

### ✅ Networking
- Retrofit configurado con interceptores
- Manejo de autenticación con API Key
- Logging para debugging
- Timeouts configurados (30 segundos)

### ✅ UI/UX
- Material Design 3
- Tema personalizado con colores morado/blanco
- Burbujas de chat diferenciadas (usuario vs IA)
- Auto-scroll a nuevos mensajes
- Input adaptativo con teclado

### ✅ Manejo de Estados
- Estados del chat (Initial, Loading, Success, Error)
- Flow para reactividad
- Coroutines para operaciones asíncronas

## Próximos Pasos para Expandir

1. **Agregar indicador de carga**: Mostrar "escribiendo..." cuando la IA está procesando
2. **Manejo de errores mejorado**: Diálogos informativos para diferentes tipos de error
3. **Persistencia local**: Room Database para guardar historial
4. **Compartir recomendaciones**: Integración con intents de Android
5. **Búsqueda en Spotify/Apple Music**: Deep links a plataformas de música
6. **Modo offline**: Caché de conversaciones anteriores
7. **Temas personalizables**: Modo oscuro y otros esquemas de color

## Costos de la API

⚠️ **IMPORTANTE**: La API de OpenAI es de pago. Considera lo siguiente:

- **GPT-3.5-turbo**: ~$0.002 por 1K tokens (económico)
- **GPT-4**: Más costoso pero mejores respuestas
- Configura **límites de uso** en tu cuenta de OpenAI
- Monitorea el consumo en el dashboard de OpenAI

## Solución de Problemas

### Error: "Failed to connect"
- Verifica que tengas conexión a Internet
- Comprueba que la API Key sea válida
- Revisa que no hayas excedido los límites de tu cuenta

### Error: "Unauthorized"
- La API Key es incorrecta o ha expirado
- Genera una nueva key en OpenAI Platform

### La app se cierra al enviar mensaje
- Revisa los logs en Logcat
- Verifica que hayas agregado el permiso de Internet en el Manifest

### No aparecen las respuestas
- Espera unos segundos (la API puede tardar)
- Verifica el LoggingInterceptor en Logcat para ver la respuesta HTTP

## Licencia
Proyecto educativo - Uso libre

## Contacto
Para dudas o sugerencias sobre el proyecto TuneChat
