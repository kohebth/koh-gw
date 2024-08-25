@extends ('backend.layouts.app')

@section('title') Create Role @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item route='{{route("backend.roles.index")}}' icon='c-icon cil-people' >
        Roles
    </x-backend-breadcrumb-item>
    <x-backend-breadcrumb-item type="active">Create</x-backend-breadcrumb-item>
</x-backend-breadcrumbs>
@endsection

@section('content')

<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-8">
                <h4 class="card-title mb-0">
                    <i class="c-icon cil-people"></i> {{ __('labels.backend.roles.index.title') }}
                    <small class="text-muted">{{ __('labels.backend.roles.create.action') }} </small>
                </h4>
            </div>
        </div>
        <!--/.row-->

        <hr>

        <div class="row mt-4">
            <div class="col">

                {{ html()->form('POST', route('backend.roles.store'))->class('form-horizontal')->open() }}
                    {{ csrf_field() }}

                    <div class="form-group row">
                        {{ html()->label(__('labels.backend.roles.fields.name'))->class('col-md-2 form-control-label')->for('name') }}

                        <div class="col-md-10">
                            {{ html()->text('name')
                                ->class('form-control')
                                ->placeholder(__('labels.backend.roles.fields.name'))
                                ->attribute('maxlength', 191)
                                ->required() }}
                        </div>
                    </div><!--form-group-->

                    <div class="form-group row">
                        {{ html()->label('Abilities')->class('col-md-2 form-control-label') }}

                        <div class="col-12 col-sm-10">
                            <div class="table-responsive-sm">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Permissions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                           <td>
                                               @if ($permissions->count())
                                                   @foreach($permissions as $permission)
                                                       <div class="checkbox">
                                                           {{ html()->label(html()->checkbox('permissions[]', old('permissions') && in_array($permission->name, old('permissions')) ? true : false, $permission->name)->id('permission-'.$permission->id) . ' ' . $permission->name)->for('permission-'.$permission->id) }}
                                                       </div>
                                                   @endforeach
                                               @endif
                                           </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div><!--form-group-->

                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <x-buttons.create moduleName="roles">{{__('Create')}}</x-buttons.create>
                                <x-buttons.cancel>{{__('Cancel')}}</x-buttons.cancel>
                            </div>
                        </div>
                    </div>
                {{ html()->form()->close() }}
            </div>
        </div>
    </div>

    <div class="card-footer">
        <div class="row">
            <div class="col">
                <small class="float-right text-muted">

                </small>
            </div>
        </div>
    </div>
</div>

@endsection
