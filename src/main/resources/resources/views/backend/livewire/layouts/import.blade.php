@extends ('backend.layouts.app')

@section('title') Import {{ucfirst($moduleName)}} @endsection

@section('breadcrumbs')
    <x-backend-breadcrumbs>
        <x-backend-breadcrumb-item type="active" icon='fas fa-cogs'>{{ convertSnakeToTitle($moduleName) }}</x-backend-breadcrumb-item>
    </x-backend-breadcrumbs>
@endsection

@section('content')
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-8">
                    <h4 class="card-title mb-0">
                        <i class="fas fa-cogs"></i> {{ convertSnakeToTitle($moduleName) }}
                        <small class="text-muted"> {{__("Import CSV")}}</small>
                    </h4>
                </div>
                <div class="col-4">
                    <div class="float-right">
                        @if(!isset($isHideDownload) || $isHideDownload == false)
                            @can("import_{$moduleName}")
                                <x-buttons.download moduleName="{{$moduleName}}"
                                                  route='{{ route("backend.{$moduleName}.download") }}'> {{__('Download CSV template')}}
                                </x-buttons.download>
                            @endcan
                        @endif
                    </div>
                </div>
            </div>
            <hr>
            {{$slot}}
        </div>
    </div>
@endsection
