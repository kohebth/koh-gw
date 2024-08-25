@extends ('backend.layouts.app')

@section('title') List {{ucfirst($moduleName)}} @endsection

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
                        <small class="text-muted">List </small>
                    </h4>
                </div>
                <div class="col-4">
                    <div class="float-right">
                        @if(!isset($isHideCreate) || $isHideCreate == false)
                        <x-buttons.create moduleName="{{$moduleName}}"
                                          route='{{ route("backend.{$moduleName}.create") }}'> {{__('Create')}}
                        </x-buttons.create>
                        @endif

                        @if(isset($isShowImport))
                            <x-buttons.import
                                moduleName="{{$moduleName}}"
                                route='{{ route("backend.{$moduleName}.import") }}'> {{__('Import')}}
                            </x-buttons.import>
                        @endif

                        @if(isset($isForceSeverUpdate))
                            <x-buttons.force-server-update
                                moduleName="{{$moduleName}}"
                                route='{{ route("backend.{$moduleName}.forceUpdate") }}'> {{__('Force Server Update')}}
                            </x-buttons.force-server-update>
                        @endif
                    </div>
                </div>
            </div>
            <hr>
            {{$slot}}
        </div>
    </div>

@endsection
